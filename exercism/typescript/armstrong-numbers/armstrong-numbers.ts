export class ArmstrongNumbers {
    public static isArmstrongNumber = (num: number): boolean => {
        const numInString:string = String(num);
        const numberOfDigits:number = numInString.length;
        return num === Array.from(numInString).map(Number).reduce((accumulator, currentValue) => accumulator + currentValue ** numberOfDigits, 0);
    }
}