def slices(series, length):
    series_size = len(series)
    if length <= 0 or length > series_size:
        raise ValueError("Given length is too large")

    return [series[i:i+length] for i in range(0, series_size-length+1)]
