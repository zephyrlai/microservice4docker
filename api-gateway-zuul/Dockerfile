FROM ccr.ccs.tencentyun.com/zephyrlai/centos-jdk8-tomcat8:0.0.1
MAINTAINER zephyrlai
COPY api-gateway-zuul-1.5.8.RELEASE.jar /myJarPacks/api-gateway-zuul-1.5.8.RELEASE.jar
RUN yum -y install nc
ENTRYPOINT ["nc","-l","8080"]
ENTRYPOINT ["java","-jar","/myJarPacks/api-gateway-zuul-1.5.8.RELEASE.jar"]