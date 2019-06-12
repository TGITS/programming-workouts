# Side notes

The following solution seems to be correct but has some flaw :

```javascript
function isLeapYear( year:number ) {
    return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
}

export default isLeapYear
```

As a matter of fact the order of operation matters:

* 75% of all years cannot be leap years because they are not multiples of 4; test year % 4 === 0 first
* 98.97% of all years that are multiples of 4 are not multiples of 100; test year % 100 !== 0 second
* 1.03% of all years that are multiples of 4 are also multiples of 100 and 400; test year % 400 === 0 third

Although the exposed solution is correct in output, the check year % 400 is only true for 1.03% years that are divisible by 4.
Always try to put the common path first.
Logically, following the instructions putting this check first is incorrect.

Thus a more correct solution is :

```javascript
function isLeapYear( year:number ) {
    return (year % 4 == 0 && year % 100 != 0) ||(year % 400 == 0);
}

export default isLeapYear
```

However there is something else to keep in mind: as it stands, in the previous solution the check year % 400 is executed for all years that don't match year % 4 == 0, and for these (75% of all years), always returns false, because if something is not divisible by 4 it can not be divisible by 400. We can refactor this a little bit.

```javascript
function isLeapYear( year:number ) {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
}

export default isLeapYear
```

