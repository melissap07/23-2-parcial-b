FROM centos:7.6.1810

RUN yum install java

COPY finexo-html /var/www/html

CMD apachectl -D FOREGROUND