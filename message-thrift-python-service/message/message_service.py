#
from message.api import MessageService
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer

import smtplib
from email.mime.text import MIMEText
from email.header import Header

sender = "zephyrlai@163.com"
authCode = "abc123"


class MessageServiceHandler:
    def sendMobileMessage(self, mobile, message):
        print("send mobile message")
        return True

    def sendEmailMessage(self, email, message):
        print("send email message")
        messageObj = MIMEText(message, "plain", "utf-8")
        messageObj['From'] = sender
        messageObj['To'] = email
        messageObj['Subject'] = Header("哈哈，这里是代码发送的邮件",'utf-8')
        smtpObj = smtplib.SMTP('smtp.163.com')
        smtpObj.login(sender,authCode)
        smtpObj.sendmail(sender,[email],messageObj.as_string())
        print("send email succ")
        return True


if __name__ == '__main__':
    handler = MessageServiceHandler()
    processor = MessageService.Processor(handler)
    transprot = TSocket.TServerSocket("localhost","9090")
    tfactory = TTransport.TFramedTransportFactory()
    pfactory = TBinaryProtocol.TBinaryProtocolFactory()

    server = TServer.TSimpleServer(processor, transprot, tfactory, pfactory)
    print("python thrift server start")
    server.serve()
    print("python thrift server exit")

