
var ddshop = {


    registerMenuEvent: function () {
        alert("zzzz");
    },
    pagesize: function () {
        $('#tb').datagrid({
                url:'items',
                //隔行变色，斑马线效果
                striped:true,
                //添加分页工具栏
                pagination:true,
                //每行的前面显示行号
                rownumbers:true,
                //使得数据表格自适应填充父容器
                fit:true,
                //默认显示10条，这样的话就显示20条
                pageSize:20,
                //分页列表
                pageList:[20,50,100],
                //多列排序
                multiSort:true,
                //列属性
                columns:[[
                //field title width列属性
                {field: 'ck', checkbox: true},
                {field: 'id', title: '商品编号', width: 100,sortable:true},
                {field: 'title', title: '商品名称', width: 100,sortable:true},
                {field: 'catname', title: '商品类型', width: 100},
                {field: 'statusName', title: '商品状态', width: 100},
                {field: 'price', title: '商品价格',formatter:function (value,row,index) {
                        var number = new Number(value/100);
                        return number.toFixed(2);
                    }},
                    {field: 'created', title: '创建时间',formatter:function (value,row,index) {
                        return moment(value).format("L");
                    }},
                    {field: 'updated', title: '更新时间',formatter:function (value,row,index) {
                        return moment(value).format("L");
                    }}
            ]],
             toolbar : "#toolbar"
        })
    }

};



