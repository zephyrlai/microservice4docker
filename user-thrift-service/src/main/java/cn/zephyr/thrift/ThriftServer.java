package cn.zephyr.thrift;

import cn.zephyr.thrift.user.UserService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Author: zephyrLai
 * @Description: TODO
 * @Date: 2019/4/10 14:28
 */
@Configuration
public class ThriftServer {

    @Value("${service.port}")
    private Integer servicePort;
    @Autowired
    private UserService.Iface userService;

    @PostConstruct
    public void startThriftServer(){
        TProcessor ifaceProcessor = new UserService.Processor<>(userService);
        TNonblockingServerSocket socket;
        try {
            socket = new TNonblockingServerSocket(servicePort);
            TNonblockingServer.Args arg = new TNonblockingServer.Args(socket);
            arg.processor(ifaceProcessor);
            // 帧传输
            arg.transportFactory(new TFramedTransport.Factory());
            // 二进制协议
            arg.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TNonblockingServer(arg);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
