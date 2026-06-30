# Design Patterns Practice: Factory Method

Твоя задача - исправить код так, чтобы он лучше использовал паттерн Factory Method.

> Factory Method выносит создание конкретных объектов в отдельный метод, который можно переопределять в наследниках. Клиентский код работает с общим продуктом и не знает, какой конкретный класс был создан.

## Что сейчас не так

В `Main.java` есть сервис `OrderNotificationService`, который сам решает, какое уведомление создать:

```java
switch (channel) {
    case "email" -> notification = new EmailNotification();
    case "sms" -> notification = new SmsNotification();
    case "push" -> notification = new PushNotification();
    default -> throw new IllegalArgumentException("Unknown channel: " + channel);
}
```

Сервис одновременно отвечает и за бизнес-сценарий отправки уведомления о заказе, и за выбор конкретного класса уведомления.

Это проблема: если завтра появится новый канал доставки, например `TelegramNotification`, придётся менять `OrderNotificationService`. Чем больше каналов, тем сильнее сервис превращается в набор условий и деталей создания объектов.

## Задание

Исправь структуру так, чтобы:

- `OrderNotificationService` не выбирал конкретный класс уведомления через `switch` или `if`;
- создание уведомления было вынесено в фабричный метод;
- для каждого канала доставки был свой creator-класс;
- клиентский код в `main()` выбирал нужный creator;
- добавление нового канала доставки не требовало изменения `OrderNotificationService`.

## Подсказка

Можно ввести общий продукт:

```java
interface Notification {
    void send(String recipient, String message);
}
```

И базовый creator:

```java
abstract class NotificationCreator {
    void notifyAboutOrder(String recipient, String orderId) {
        Notification notification = createNotification();
        notification.send(recipient, "Order " + orderId + " is ready");
    }

    protected abstract Notification createNotification();
}
```

Тогда `EmailNotificationCreator`, `SmsNotificationCreator` и `PushNotificationCreator` будут переопределять `createNotification()` и возвращать нужный тип уведомления.

## Самопроверка

После решения спроси себя:

- Остался ли в сервисе `switch` или `if`, который выбирает конкретный класс уведомления?
- Работает ли основная логика отправки через общий тип `Notification`?
- Можно ли добавить новый канал доставки, создав новый creator, а не меняя существующий сервис?
- Выбирается ли конкретный creator в `main()` или на внешнем уровне приложения?
