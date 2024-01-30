# README

* `python -m venv venv --prompt="hello"`
* `venv\Scripts\activate`
* `pip install fastapi`
* `pip install uvicorn`
* `pip install httpie`
* `pip install requests`
* `pip install httpx`

```python
import requests
r = requests.get("http://localhost:8000/hi")
r.json()
```

```python
import httpx
r = httpx.get("http://localhost:8000/hi")
r.json()
```

* Avec HTTPie

```txt
http localhost:8000/hi
http -b localhost:8000/hi
http -v localhost:8000/hi
```
