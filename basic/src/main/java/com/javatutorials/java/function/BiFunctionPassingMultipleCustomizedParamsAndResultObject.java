package com.javatutorials.java.function;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * 问题： 有一段java逻辑，里头有五个判断真假的函数，传入自定义参数，依次执行；返回自定义的结果，如何使用Stream优雅地实现它
 * <p>
 * 回答：在Java中，如果每个判断函数需要自定义参数，并且要依次执行，同时返回自定义的结果，可以使用Stream和Optional来实现。
 * 以下是一个示例代码，演示了如何使用Stream和自定义的函数接口来实现这个逻辑：
 */
public class BiFunctionPassingMultipleCustomizedParamsAndResultObject {
    public static void main(String[] args) {
        //BiFunction<CustomParam, AnotherParam, Result>：使用BiFunction接口来表示一个接受两个自定义参数并返回自定义结果的函数。
        //List<BiFunction<CustomParam, AnotherParam, Result>> conditions：存储所有条件的列表。
        List<BiFunction<CustomParam, AnotherParam, Result>> conditions = Arrays.asList(
                BiFunctionPassingMultipleCustomizedParamsAndResultObject::condition1,
                BiFunctionPassingMultipleCustomizedParamsAndResultObject::condition2,
                BiFunctionPassingMultipleCustomizedParamsAndResultObject::condition3,
                BiFunctionPassingMultipleCustomizedParamsAndResultObject::condition4,
                BiFunctionPassingMultipleCustomizedParamsAndResultObject::condition5
        );

        //List<CustomParam> customParams 和 List<AnotherParam> anotherParams：分别存储所有自定义参数的列表，每个参数列表根据实际情况定义。
        List<CustomParam> customParams = Arrays.asList(
                new CustomParam(), // condition1的参数
                new CustomParam(), // condition2的参数
                new CustomParam(), // condition3的参数
                new CustomParam(), // condition4的参数
                new CustomParam()  // condition5的参数
        );

        List<AnotherParam> anotherParams = Arrays.asList(
                new AnotherParam(), // condition1的参数
                new AnotherParam(), // condition2的参数
                new AnotherParam(), // condition3的参数
                new AnotherParam(), // condition4的参数
                new AnotherParam()  // condition5的参数
        );

        //Stream API与短路逻辑：通过conditions.stream().map(...).filter(...).findFirst()进行遍历，找到第一个返回false的结果并停止后续的条件判断。
        Optional<Result> result = conditions.stream()
                .map(condition -> condition.apply(
                        customParams.get(conditions.indexOf(condition)),
                        anotherParams.get(conditions.indexOf(condition))
                ))
                .filter(res -> !res.isSuccess())
                .findFirst();

        if (result.isPresent()) {
            System.out.println("At least one condition is false. Result: " + result.get());
        } else {
            System.out.println("All conditions are true");
        }
    }

    static Result condition1(CustomParam param1, AnotherParam param2) {
        // Your condition logic here
        return new Result(true, "Condition1 passed");
    }

    static Result condition2(CustomParam param1, AnotherParam param2) {
        // Your condition logic here
        return new Result(true, "Condition2 passed");
    }

    static Result condition3(CustomParam param1, AnotherParam param2) {
        // Your condition logic here
        return new Result(false, "Condition3 failed");
    }

    static Result condition4(CustomParam param1, AnotherParam param2) {
        // Your condition logic here
        return new Result(true, "Condition4 passed");
    }

    static Result condition5(CustomParam param1, AnotherParam param2) {
        // Your condition logic here
        return new Result(true, "Condition5 passed");
    }

    // 定义自定义参数类
    static class CustomParam {
        // Your custom fields and methods here
    }

    static class AnotherParam {
        // Your custom fields and methods here
    }

    // 定义返回结果类
    static class Result {
        private boolean success;
        private String message;

        public Result(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "success=" + success +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
