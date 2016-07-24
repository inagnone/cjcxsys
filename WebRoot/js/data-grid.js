//数据表格底部分页栏中文设置
	function pagerFilter(data){
            if (typeof data.length == 'number' && typeof data.splice == 'function'){    // 判断数据是否是数组
                data = {
                    total: data.length,
                    rows: data
                };
            }
            var dg = $('#dg');
            var opts = dg.datagrid('options');
            var pager = dg.datagrid('getPager');
            pager.pagination({
	             beforePageText: '第',//页数文本框前显示的汉字 
	     	   	 afterPageText: '页    共 {pages} 页', 
	        	 displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
                onSelectPage:function(pageNum, pageSize){
                    opts.pageNumber = pageNum;
                    opts.pageSize = pageSize;
                    pager.pagination('refresh',{
                        pageNumber:pageNum,
                        pageSize:pageSize
                    });
                    //选择每页数据显示量后重新加载数据
                    dg.datagrid('loadData',data);
                }
            });
            if (!data.originalRows){
                data.originalRows = (data.rows);
            }
            var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
            var end = start + parseInt(opts.pageSize);
            data.rows = (data.originalRows.slice(start, end));
            return data;
        }
 		//执行分页
        $(function(){//加载数据
            $('#dg').datagrid({loadFilter:pagerFilter});
        });