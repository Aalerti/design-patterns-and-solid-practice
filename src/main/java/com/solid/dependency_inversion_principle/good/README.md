# Good Solution: Dependency Inversion Principle

В этой версии код исправлен так, чтобы он лучше соблюдал Dependency Inversion Principle.

## Что было не так в bad-версии

В плохом варианте `PasswordResetService` сам создавал внутри себя конкретный объект `SmsGateway`:

```java
private final SmsGateway smsGateway = new SmsGateway();
```

Это жёсткая зависимость модуля верхнего уровня (бизнес-логика) от модуля нижнего уровня (инфраструктура). Чтобы поменять способ доставки — например, с SMS на email — нужно было лезть внутрь `PasswordResetService` и менять его код, хотя сама логика генерации кода сброса не менялась.

## Что исправлено

- Введён интерфейс `NotificationSender` с единственным методом `send(String contact, String message)`.
- `SmsGateway` и `EmailGateway` реализуют этот интерфейс — каждый по-своему.
- `PasswordResetService` больше не создаёт конкретный класс внутри себя. Он получает `NotificationSender` через конструктор.
- В `main()` явно выбирается нужный способ доставки при создании сервиса.

## Почему теперь это DIP

`PasswordResetService` зависит от абстракции `NotificationSender`, а не от `SmsGateway` или `EmailGateway`. Оба конкретных класса тоже зависят от той же абстракции — реализуют её. Никто из них не знает друг о друге.

Чтобы добавить, например, `PushNotificationSender`, достаточно создать новый класс, реализующий `NotificationSender`. `PasswordResetService` при этом не меняется вообще.

Решение о том, какой отправитель использовать, принято в одном месте — в `main()`:

```java
PasswordResetService service = new PasswordResetService(new SmsGateway());
```

Именно так и должно быть: детали инфраструктуры подключаются снаружи, а не зашиваются в бизнес-логику.
