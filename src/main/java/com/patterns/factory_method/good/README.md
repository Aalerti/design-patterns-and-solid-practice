# Good Solution: Factory Method

В этой версии код исправлен так, чтобы он использовал паттерн Factory Method.

## Что было не так в bad-версии

В плохом варианте `OrderNotificationService` сам выбирал, какой конкретный класс уведомления создать:

```java
switch (order.channel()) {
    case "email" -> notification = new EmailNotification();
    case "sms" -> notification = new SmsNotification();
    case "push" -> notification = new PushNotification();
    default -> throw new IllegalArgumentException("Unknown channel: " + order.channel());
}
```

Из-за этого в одном классе смешались две ответственности:

- основная логика отправки уведомления о заказе;
- создание конкретного типа уведомления.

Если добавить новый канал доставки, например `TelegramNotification`, пришлось бы менять старый сервис и дописывать новую ветку в `switch`.

## Что изменили

- Удалили старый `OrderNotificationService` со `switch`.
- Добавили абстрактный creator `OrderNotificationFactory`.
- Вынесли общий сценарий отправки в метод `notifyCustomer(Order order)`.
- Вынесли создание конкретного уведомления в фабричный метод `createNotification()`.
- Создали отдельные creator-классы: `OrderEmailNotificationFactory`, `OrderSMSNotificationFactory`, `OrderPushNotificationFactory`.
- В `main()` явно выбрали нужную фабрику для каждого заказа.

Теперь общий алгоритм находится в одном месте:

```java
abstract class OrderNotificationFactory {
    void notifyCustomer(Order order) {
        Notification notification = createNotification();
        String message = "Hello, " + order.customerName() + "! Order " + order.id() + " is ready.";
        notification.send(order.recipient(), message);
    }

    protected abstract Notification createNotification();
}
```

А конкретные creator-классы решают только то, какой продукт создать:

```java
class OrderEmailNotificationFactory extends OrderNotificationFactory {
    @Override
    protected Notification createNotification() {
        return new EmailNotification();
    }
}
```

## Почему теперь это Factory Method

`OrderNotificationFactory` содержит общий алгоритм: подготовить сообщение и отправить его получателю.

Но один шаг алгоритма он не выполняет сам. Он не знает, какой именно объект `Notification` нужно создать. Вместо этого он вызывает фабричный метод:

```java
protected abstract Notification createNotification();
```

Конкретные наследники переопределяют этот метод и возвращают нужный продукт:

- `OrderEmailNotificationFactory` создаёт `EmailNotification`;
- `OrderSMSNotificationFactory` создаёт `SmsNotification`;
- `OrderPushNotificationFactory` создаёт `PushNotification`.

Код, который отправляет уведомление, работает через общий интерфейс `Notification`, а не через конкретные классы. Поэтому общий алгоритм не зависит от деталей доставки.

## Почему теперь легче расширять

Если понадобится добавить новый канал, например Telegram, не нужно менять существующий алгоритм отправки.

Достаточно добавить новый продукт:

```java
class TelegramNotification implements Notification {
    @Override
    public void send(String recipient, String message) {
        System.out.println("TELEGRAM to " + recipient + ": " + message);
    }
}
```

И новый creator:

```java
class OrderTelegramNotificationFactory extends OrderNotificationFactory {
    @Override
    protected Notification createNotification() {
        return new TelegramNotification();
    }
}
```

`OrderNotificationFactory.notifyCustomer()` при этом не меняется.

## Важный момент про список

Да, в классическом Factory Method обычно появляется отдельный creator-класс под каждый вариант продукта. Это нормально для учебного примера: так хорошо видно, где находится общий алгоритм, а где находится изменяемый шаг создания объекта.

В реальном проекте, если нужно просто выбрать обработчик по строке из списка заказов, часто используют более компактный вариант: `Map`, registry или simple factory. Но именно в этом упражнении цель - потренировать Factory Method, поэтому создание отдельных creator-классов здесь оправдано.
