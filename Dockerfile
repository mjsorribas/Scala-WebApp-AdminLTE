FROM hseeberger/scala-sbt:latest
MAINTAINER Maximiliano José Sorribas <mjsorribas@gmail.com>

EXPOSE 9000:9005
#RUN echo 'deb https://dl.bintray.com/sbt/debian /' | tee -a /etc/apt/sources.list.d/sbt.list
#RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 642AC823
#RUN apt-get -y update; \
#    apt-get -y upgrade; 
#    apt-get -y install sbt \
#    vim \
#    htop;
#RUN apt-get -y install dstat
RUN echo "Creo el directorio de apps"
RUN  mkdir /apps && cd /apps
RUN echo "copio los archivos"
#COPY /home/maximilianojosesorribas/Proyectos/Scala-WebApp-AdminLTE /apps
ADD  . /apps
WORKDIR /apps
#ENV  /home/maximilianojosesorribas/Proyectos/Scala-WebApp-AdminLTE
#ENTRYPOINT ["sbt","run"]
RUN echo "Corro el server"
RUN pwd
CMD sbt run
