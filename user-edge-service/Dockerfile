FROM ccr.ccs.tencentyun.com/zephyrlai/centos-jdk8-tomcat8:0.0.1
MAINTAINER zephyrlai
COPY user-edge-service-1.0-SNAPSHOT.jar /myJarPacks/user-edge-service-1.0-SNAPSHOT.jar
# for win-env
# ENTRYPOINT ["java","-jar","/myJarPacks/user-edge-service-1.0-SNAPSHOT.jar","--redis.host=192.168.163.128"]
# for mac-env
ENTRYPOINT ["java","-jar","/myJarPacks/user-edge-service-1.0-SNAPSHOT.jar","--redis.host=192.168.2.128"]