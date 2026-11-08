import base64
import json
import os
import time
import urllib.request

workspace = os.environ["WORKSPACE_ID"]
api_key = os.environ["DASHSCOPE_API_KEY"]
host = f"https://{workspace}.cn-beijing.maas.aliyuncs.com"
image = base64.b64encode(open("avatar.jpg", "rb").read()).decode("ascii")
prompt = open("script.txt", encoding="utf-8").read().strip()

body = json.dumps(
    {
        "model": "wan2.7-i2v-2026-04-25",
        "input": {
            "prompt": prompt,
            "media": [
                {"type": "first_frame", "url": "data:image/jpeg;base64," + image}
            ],
        },
        "parameters": {"resolution": "720P", "duration": 5, "prompt_extend": False},
    }
).encode("utf-8")

request = urllib.request.Request(
    host + "/api/v1/services/aigc/video-generation/video-synthesis",
    data=body,
    headers={
        "Authorization": "Bearer " + api_key,
        "Content-Type": "application/json",
        "X-DashScope-Async": "enable",
    },
    method="POST",
)
with urllib.request.urlopen(request) as response:
    created = json.loads(response.read().decode("utf-8"))
task_id = created["output"]["task_id"]
print("task_id", task_id)

video_url = ""
for _ in range(40):
    time.sleep(15)
    query = urllib.request.Request(
        host + "/api/v1/tasks/" + task_id,
        headers={"Authorization": "Bearer " + api_key},
    )
    with urllib.request.urlopen(query) as response:
        result = json.loads(response.read().decode("utf-8"))
    status = result["output"]["task_status"]
    print(status)
    if status == "SUCCEEDED":
        video_url = result["output"]["video_url"]
        break
    if status in ("FAILED", "CANCELED", "UNKNOWN"):
        raise SystemExit(result)

if not video_url:
    raise SystemExit("超时，没有视频地址")

urllib.request.urlretrieve(video_url, "avatar.mp4")
print("已保存 avatar.mp4")
