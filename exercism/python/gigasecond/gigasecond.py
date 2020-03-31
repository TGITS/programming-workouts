from datetime import timedelta, datetime

def add(moment):
    gigasecond = timedelta(seconds=1e9)
    return moment + gigasecond
