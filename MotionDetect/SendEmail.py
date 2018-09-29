
from datetime import datetime
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
#from email.mime.image import MIMEImage
import cv2
import os
import time

def sendmail():

	fromaddr = "smartdepot0@gmail.com"
	toaddr = "kritanu82@gmail.com"
	msg = MIMEMultipart()
	msg['From'] = fromaddr
	msg['To'] = toaddr
	msg['Subject'] = "Alert! Smart Depot "
	body = 'Motion of an Intruder has been detected.\nTime: %s' % str(datetime.now())
	msg.attach(MIMEText(body, 'plain'))
	#image = MIMEImage(img_data, name=os.path.basename("C:/Users/kritanu/Desktop/motion-detection-master"))
	

	#fp = open('intruder.jpg', 'rb')
	#image = MIMEImage(fp.read())
	#fp.close()

	#msg.attach(image)


	server = smtplib.SMTP('smtp.gmail.com', 587)
	server.starttls()
	server.login(fromaddr, "smartdepot!@#")
	text = msg.as_string()
	server.sendmail(fromaddr, toaddr, text)
	server.quit()