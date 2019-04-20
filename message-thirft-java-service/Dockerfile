FROM ccr.ccs.tencentyun.com/zephyrlai/centos-jdk8-tomcat8:0.0.1
MAINTAINER zephyrlai
COPY message-thirft-java-service-2.1.3.RELEASE.jar /myJarPacks/message-thirft-java-service-2.1.3.RELEASE.jar
RUN yum -y install nc
ENTRYPOINT ["nc","-l","9090"]
ENTRYPOINT ["java","-jar","/myJarPacks/message-thirft-java-service-2.1.3.RELEASE.jar"]