import firebase_admin
from firebase_admin import credentials
from firebase_admin import messaging

from .config import Config

cred_path = Config.cred_path
cred = credentials.Certificate(cred_path)
firebase_admin.initialize_app(cred)



async def send_push_notification(token, title, body, data):

    message = messaging.Message(
        notification=messaging.Notification(
            title=title,
            body=body
        ),
        data=data,
        token=token
    )

    response = messaging.send(message)

    print('Successfully sent message:', response)

