FROM ccr.ccs.tencentyun.com/zephyrlai/centos-jdk8-tomcat8:0.0.1
MAINTAINER zephyrlai
COPY course-dubbo-service-1.5.8.RELEASE.jar /myJarPacks/course-dubbo-service-1.5.8.RELEASE.jar
RUN yum -y install nc
ENTRYPOINT ["nc","-l","9090"]
# for win-env
# ENTRYPOINT ["java","-jar","/myJarPacks/course-dubbo-service-1.5.8.RELEASE.jar","--dubbo.address=192.168.163.128"]
# for mac-env
ENTRYPOINT ["java","-jar","/myJarPacks/course-dubbo-service-1.5.8.RELEASE.jar","--dubbo.address=192.168.2.139"]
