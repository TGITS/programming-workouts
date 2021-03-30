events_by_code = {10000: lambda x: x.reverse(), 1000: lambda x: x.append("jump"), 100: lambda x: x.append(
    "close your eyes"), 10: lambda x: x.append("double blink"), 1: lambda x: x.append("wink")}


def commands(number):
    binary = int(bin(number)[2:])

    actions = []
    for code in reversed(sorted(events_by_code.keys())):
        if binary >= code:
            binary -= code
            actions.append(code)

    secret_handshake = []
    for code in reversed(actions):
        events_by_code[code](secret_handshake)

    return secret_handshake
