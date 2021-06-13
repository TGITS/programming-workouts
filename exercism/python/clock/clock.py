class Clock:
    def __init__(self, hour: int, minute: int):
        self.minute = minute % 60
        overflow = minute // 60
        self.hour = (hour + overflow) % 24

    def __repr__(self) -> str:
        return f'{str(self.hour).rjust(2, "0")}:{str(self.minute).rjust(2, "0")}'

    def __eq__(self, other) -> bool:
        if isinstance(other, Clock):
            return self.hour == other.hour and self.minute == other.minute
        return False

    def __add__(self, minutes: int):
        return Clock(self.hour, self.minute + minutes)

    def __sub__(self, minutes: int):
        return Clock(self.hour, self.minute - minutes)
