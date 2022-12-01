class Rectangle {
    constructor(height, width) {
        this.height = height;
        this.width = width;
    }
}

const r = new Rectangle(5, 7);
console.log(r);
console.log(`height : ${r.height}, width : ${r.width}`);