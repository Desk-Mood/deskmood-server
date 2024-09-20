import boto3
from botocore.client import Config
import os
import json
import time
import sys

if len(sys.argv) <= 3:
    print("not enough system arguments")
    exit()

ACCESS_KEY_ID = sys.argv[1]
ACCESS_SECRET_KEY = sys.argv[2]
BUCKET_NAME = sys.argv[3]

META_DATA = {
    "info": {
        "title": "Deskmood Admin API",
        "description": "",
        "version": "1.0.0-SNAPSHOT"
    },
    "servers": [
        {
            "url": "https://dev.deskmood.kr",
            "description": "dev server"
        },
        {
            "url": "https://deskmood.kr",
            "description": "live server"
        },
        {
            "url": "http://localhost:8080",
            "description": "local server"
        }
    ],
    "authorize": {
        "enable": True,
        "securitySchemes": {
            "bearerAuth": {
                "type": "http",
                "scheme": "bearer",
                "bearerFormat": "JWT"
            }
        },
        "security": [
            {
                "bearerAuth": []
            }
        ]
    }
}

os.system("./gradlew :deskmood-admin:admin-api:clean")
os.system("./gradlew :deskmood-admin:admin-api:openapi3")

with open("./deskmood-admin/admin-api/build/api-spec/openapi3.json", "r") as oas:
    key = "api-docs/oas3-admin.json"
    oas_data = json.load(oas)

    oas_data["info"] = META_DATA["info"]
    oas_data["info"]["description"] = "last updated (UTC) : " + time.strftime('%Y-%m-%d %H:%M:%S')
    oas_data["servers"] = META_DATA["servers"]

    if META_DATA["authorize"]["enable"]:
        oas_data["components"]["securitySchemes"] = META_DATA["authorize"]["securitySchemes"]
        oas_data["security"] = META_DATA["authorize"]["security"]

    key = "api-docs/oas3-admin.json"

    client = boto3.client(
        's3',
        aws_access_key_id=ACCESS_KEY_ID,
        aws_secret_access_key=ACCESS_SECRET_KEY,
        region_name="ap-northeast-2"
    )

    client.delete_object(Bucket=BUCKET_NAME, Key=key)

    if os.path.isfile("./oas3_admin.json"):
        os.remove("./oas3_admin.json")

    with open("./oas3_admin.json", "w", encoding="utf-8") as swagger:
        json.dump(oas_data, swagger, ensure_ascii=False, indent="\t")

    data = open('./oas3_admin.json', 'rb')
    s3 = boto3.resource(
        's3',
        aws_access_key_id=ACCESS_KEY_ID,
        aws_secret_access_key=ACCESS_SECRET_KEY,
        config=Config(signature_version='s3v4')
    )
    s3.Bucket(BUCKET_NAME).put_object(
        Key=key, Body=data, ContentType='json'
    )
