

## Настройка Report Portal-краткая инструкция

1. Создать в проекте файл docker-compose.yml  и добавить в него содержимое из https://github.com/reportportal/reportportal/blob/master/docker-compose.yml ](https://github.com/reportportal/reportportal/blob/master/docker-compose.yml) файл.
2. Подключиться к VPN, чтобы скачать необходимые элементы для отчета Report Portal.
3. Запустить контейнеры командой `docker-compose -p reportportal up`. Ждать полной загрузки контейнеров.
4. После загрузки перейти по адресу http://localhost:8080/ и авторизоваться по следующему логину/паролю: superadmin/erebus.
5. Перейти в настройки профиля и скопировать из раздела CONFIGURATION EXAMPLES во вкладке JAVA все строки REQUIRED и вставить их в проект в файл reportportal.properties (разместить файл в `src/test/resources`). 
6. Подключить необходимые зависимости в файле`build.gradle` (см. файл "build.gradle"). 
7. В папке `src/test/resources` добавить каталог `META-INF/services`, в нем создать файл `org.junit.jupiter.api.extensions.Extension` с содержимым `com.epam.reportportal.junit5.ReportPortalExtension` (см. файл `org.junit.jupiter.api.extensions.Extension`).
8. В папке `src/test/resources` добавить файлы `logback.xml`, `log4j2.xml`.
9. В папке `src/test/java/ru/netology/util` добавить класс `ScreenShooterReportPortalExtension.java` и логгер `LoggingUtils.java`.
10. В классе DeliveryTest.java добавить аннотацию `@ExtendWith({ScreenShooterReportPortalExtension.class})`, указать в коде теста метод `LogInfo(*)` для сохранения логов по каждому действию (см. java/DeliveryTest.java).
11. Запустить приложение через `java -jar artifacts/app-card-delivery.jar`. 
12. Запустить тест командой `./gradlew clean test --info --debug`.
13. После полного прохождения теста просмотреть отчет по адресу http://localhost:8080/ в разделе запуски (launches на англ-м).
