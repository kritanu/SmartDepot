import cv2

vidcap = cv2.VideoCapture(0)
vidcap.set(0,20000)      # just cue to 20 sec. position
success,image = vidcap.read()
if success:
    cv2.imwrite("frame20sec.jpg", image)
    cv2.waitKey(0)        