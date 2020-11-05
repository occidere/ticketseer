# ticketseer
뮤지컬, 콘서트 등의 각종 티켓 정보 업데이트와 상영 현황을 Line 메신저로 알려줍니다.

<br>

## 뮤지컬 알림 서비스

### 신규 뮤지컬 등록 알림

- 인터파크, yes24 등 각종 사이트에 새롭게 올라오는 뮤지컬 정보를 Line 메신저로 알려줍니다

    ![image](https://user-images.githubusercontent.com/20942871/97175425-d3df5080-17d6-11eb-9568-f9a953575776.png)

- 현재 지원되는 사이트: 인터파크

<br>

### 현재 공연중 & 예매 가능한 뮤지컬 알림

- 하루 1번 현재 공연중이거나 예매가 가능한 뮤지컬 정보를 Line 메신저로 알려줍니다.

    ![image](https://user-images.githubusercontent.com/20942871/97175809-68e24980-17d7-11eb-912f-753ccb354ecf.png)

- 현재 지원되는 사이트: 인터파크
- 비고: 메시지 포맷 수정중...

<br>

## 알람 구독
- Line Bot QR

    <img src="https://user-images.githubusercontent.com/20942871/97531201-09628480-19f7-11eb-8c4a-6e10721aa15c.png" width=200/>

<br>

## Build & Run

### Gradle
```bash
# build
gradle build -x test

# run
java -jar build/libs/ticketseer-0.0.1-SNAPSHOT.jar \
    --job.name=${JOB_NAME} \
    --es.endpoint=${ES_ENDPOINT} \
    --line.channel.token=${LINE_CHANNEL_ID}
```

### Run Parameters
- `job.name`: `newMusicalJob`, `onScreenMusicalJob`
- `es.endpoint`: Elasticsearch endpoint. ex) `localhost:9200`
- `line.channel.token`: Line messenger channel token. ex) `AkFx3j/+sD....`
