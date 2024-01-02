def transform(legacy_data):
    return {e.lower(): k for (k, v) in legacy_data.items() for e in v}
