FROM nginx:stable-alpine as production-stage

RUN mkdir /etc/url
COPY ./static /etc/url/static

RUN rm -rf /etc/nginx/conf.d/default.conf
COPY ./nginx/Config/default.conf /etc/nginx/conf.d/default.conf

RUN mkdir /etc/sslkeys
COPY ./nginx/keys/gospring.co.kr.pem /etc/sslkeys/gospring.co.kr.pem
COPY ./nginx/keys/www_gospring.co.kr.key /etc/sslkeys/www_gospring.co.kr.key
#인증서 발급되면 여기에 인증서 복사 하는 내용도 넣어주자

EXPOSE 443
ENTRYPOINT ["nginx", "-g", "daemon off;"]