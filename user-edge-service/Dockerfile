FROM ccr.ccs.tencentyun.com/zephyrlai/centos-jdk8-tomcat8:0.0.1
MAINTAINER zephyrlai
ENTRYPOINT ["mkdir","/myJarPacks"]
COPY user-edge-service-1.0-SNAPSHOT.jar /myJarPacks/user-edge-service-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/myJarPacks/user-edge-service-1.0-SNAPSHOT.jar","--redis.host=192.168.163.128"]