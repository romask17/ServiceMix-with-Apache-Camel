# ServiceMix with Apache Camel

Test project, which contains examples of working with `Apache Camel` in `ServiceMix` 
osgi container. 

## Модули проекта:

* mail - Using `mail` Component of Apache Camel
* csv - Using `camel-csv` Component of Apache Camel

### Установка дополнительных компонентов

Вводятся в `karaf` консоли:

```bash
karaf@root>feature:install camel-csv #для работы с csv
karaf@root>bundle:install -s mvn:org.apache.camel/camel-core-osgi/2.16.5
```

## Команды `karaf` консоли:
```bash
la 			 # просмотр всех бандлов. Показывает статус всех бандлов, их версия, номер установки, название
stop <номер бандла>	 # останавливает нужный бандл
start <номер бандла> 	 # запускает нужный бандл
install <путь до jar>	 # установка бандла. В качестве пути может выступать как адрес в файловой системе, так и maven путь(mvn:<groupId>/<artifactId>/<version>)
uninstall<номер бандла>  # удаление бандла
log:display		 # покажет текущий лог ServiceMix
log:clear		 # очистка лога в консоли ServiceMix
кнопка TAB		 # показывает или завершает ввод команды в консели
Ctrl+D 			 # закрывает ServiceMix
```
	
	
## Статусы бандлов
	Active			- бандл запущен
	Resolved		- бандл остановлен
	GracePeriod		- бандл не может запустить из-за отсутствия какой-то зависимости. Данный период по-умолчанию длится 5 минут, потом бандл переходит в статус Failure
	Failure			- бандл не смог запуститься из-за ошибок в самом бандле
	
## Структура папок ServiceMix
	bin    - папка для запуска ServiceMix в разных ОС
	data   - рабочая папка. В данной папке находятся все устанвленные бандлы, база данных(kahadb) для брокера ActiveMQ, лог файл работы ServiceMix
	deploy - папка для быстрой установки бандла в контейнер. Скопировав в данную папку jar, ServiceMix автоматически установит данный бандл в контейнер. При удалении jar - бандл удалится из контейнера
	etc    - папка с cfg и другими файлами настройки. По-умолчанию, если не указывать путь до файлов, то ServiceMix ищет файл относительно данной папки
	
## Мониторинг hawtio - http://hawt.io/ для ServiceMix
	Для мониторинга работоспособности ServiceMix можно пользоваться стандартной консолью, но можно установить и сторонний фронт для данный целей.
	Для устновки фронта необходимо:
	- прописать репозиторий к hawtio 
		в консоли ServiceMix ввести - feature:repo-add hawtio 1.4.57
			версия 1.4.57 для ServiceMix 6.1.1 и ниже. Если не указать версию, то будет установлен репозиторий с последней версией
	- установить hawtio
		в консоли ServiceMix ввести - feature:install hawtio/1.4.57
	- в браузере ввести - http://localhost:8181/hawtio
	- логин/пароль - smx/smx
