function print_star(count) {
    if (isNaN(count) || count < 0) {
        document.write("입력 오류입니다.");
        return;
    }
    for (let i = 0; i < parseInt(count); i++) {
        for (let j = 0; j <= i; j++) {
            document.write("*\n");
        }
        document.write('<br>');
    }
}

let input_count = prompt("정수를 입력하시오: ")
print_star(input_count);
