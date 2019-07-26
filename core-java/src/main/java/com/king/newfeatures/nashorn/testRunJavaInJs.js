var BigDecimal = Java.type('java.math.BigDecimal');

function calculate(amount, percentage) {

    var result = new BigDecimal(amount).multiply(
        new BigDecimal(percentage)).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_EVEN);

    return result.toPlainString();
}

var result = calculate(568000000000000000023,13.9);
print(result);

/*
*
* 我们使用 jjs 命令执行以上脚本，输出结果如下：

$ jjs sample.js

* */