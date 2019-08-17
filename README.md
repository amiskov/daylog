# DayLog

А что если в Postgres записывать JSON для дня? Но так я не получу простого способа для автокомплита дел, трекания повторяющихся.

deeds
- id
- title

grades
- id
- title

entry
- id
- deed_id
- grade_id
- datetime_start (дату и время может отдельно хранить, чтоб быстрее доставать из базы?)
- duration (дела могут пересекаться?)



generated using Luminus version "3.46"




## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein run 

## License

Copyright © 2019 FIXME


## Book notes
Работа с Luminus идет через REPL: `lein repl` в корне.

REPL по-умолчанию загрузит неймспейс `user`, доступные команды можно посмотреть в `env/dev/clj/user.clj` (миграции и сервер).