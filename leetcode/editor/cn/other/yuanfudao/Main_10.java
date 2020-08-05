package yuanfudao;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-01 20:08
 **/

public class Main_10 {
    /**
     *  小猿参加了猿辅导的编程培训课程，课后老师给大家留了作业，要求写一个简化版的模版解析器。
     *  需要具备的功能是：给一个模版字符串注入数据，输出模版解析后的字符串，保证了模版中的变量一定存在于注入的对象数据中。
     *
     * 注入的数据是一个对象，表现形式是{key1: value2，key2: value2, key3: value3},
     * key是一个字符串，value可以是对象、布尔值、字符串和包含相同元素的数组，【key：value】对数为正整数。
     * 模版字符串由一系列标签元素和普通元素组成，
     * 标签元素的形态是：<标签名字 aaa=“vvv” bbb="ttt" ccc="ppp">xxxxx</标签名字>，
     * 其中标签名字是一个由小写字母组成的长度大于等于1的字符串，比如示例模版中的div、button、ul、li等，
     * 并且成对出现，比如<div>和</div>、<button>和</button>。标签元素上有0个或多个属性，诸如aaa、bbb、ccc，
     * 每个属性都有一个取值, 属性可以是下述解析规则1和2。
     * xxxxx是该标签元素的内容，可以是标签元素、也可以是普通元素。
     * 普通元素是一个常量或下述解析规则的动态值，常量是字符串、数字、空格、换行符和可视化的符号。
     *
     *
     * 解析规则如下：
     *
     * 1.解析指令 【y-if="{{xxx}}"】，根据双大括号中变量值判断当前标签元素是否存在，取值范围如下：
     *    -- true，类型boolean，保留标签元素及其子元素；
     *    -- false，类型boolean，移除标签元素及其子元素；
     *    -- undefined，表达式xxx对应的变量不存在，移除标签元素及其子元素；
     * 2.解析指令 【y-for="xxx, yyy in zzz"】，对当前标签元素做for循环输出，
     * 每个输出元素用换行符‘\n’分隔。指令的值域格式，如示例中的 lesson, index in list,
     * 其中，变量list是一个数组，可以在注入的数据中找到，数组中的每个元素类型一致。变量index表示数组的索引，
     * 变量lesson表示这个数组中对应索引index的元素，二者顺序固定。数组索引和元素的变量名（index和lesson）
     * 可以是一个任意的由小写字母组成的长度大于等于1的字符串。
     *
     * 注：y-for和y-if不会同时出现在一个标签中。
     *
     * 3.注入动态值 {{xxx.yyy}}，双括号表示获取注入数据中的变量值，
     * xxx.yyy是注入数据data的xxx属性对应的对象的yyy属性的值，该变量值是一个string，
     * 比如示例中的{{lesson.teacher}}，输出lesson对象的teacher属性的值。如果注入的变量值不存在，则输出空字符串。
     * 4.删除注释所在的一行，注释以<!--开头、以-->结尾，独占一行，如示例中的<!-- 卡片区域 -->。
     * 5.除了注释以外，标签元素、解析指令y-if、解析指令y-for 都存在嵌套的情况。
     *
     * 输入描述:
     * 输入分为两部分，用2个空行分开
     * 第一部分共N行，是一个对象Object，包含模版字符串中需要的所有数据，数据类型有string、boolean、array、object；
     *
     * 第二部分共M+1行，前M行是一个模版字符串，至少包含上述的一条规则M > 1 ，最后一行是字符串“end”，表示输入结束。
     *
     * {
     *   "isMain": false,
     *   "list": []
     * }
     *
     *
     * <div class="head">
     * <button y-if="{{isMain}}">首页</button>
     * </div>
     * <ul class="content">
     * <!-- 卡片区域 -->
     * <li class="card isMain" y-for="lesson, index in list">
     *   <div class="card-title">
     *     	<i y-if="{{lesson.label}}">{{lesson.label.type}}</i>
     *                {{lesson.title}}
     *  		</div>
     *     班课<span class="bold">-</span>老师：{{lesson.teacher}}
     *     <div class="lesson-time">{{lesson.time}}<i class="tt"></i></div>
     *   </li>
     * </ul>
     * end
     *
     *
     *
     * <div class="head">
     *
     * </div>
     * <ul class="content">
     *
     * </ul>
     **/

}
