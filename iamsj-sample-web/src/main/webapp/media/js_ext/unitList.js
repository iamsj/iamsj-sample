
var listForm = {		
	initTable : function(param) {
		/*
		 * Initialse DataTables, with no sorting on the 'details' column
		 */
		listForm.oTable = $('#listForm').dataTable({
			"bFilter" : false,
			"bSort" : true, // 排序功能
			"bProcessing" : false,// 设置异步请求时，是否有等待框。
			"sAjaxSource" : basePathUrl + '/account/unitListData.json',// 请求url
			"fnServerParams" : function(aoData) {
				aoData.push();
			},
			"sServerMethod" : "get",
			"bServerSide" : true, // 异步请求
			"aoColumns" : param.columns,
			"fnServerData" : function(sSource, aoData, fnCallback) {
				$.ajax({
					"dataType" : 'json',
					"type" : "get",
					"url" : sSource,
					"data" : aoData,
					"success" : fnCallback,
					"timeout" : 15000
				// 连接超时时间
				});
			},
			"aoColumnDefs" : [ {
				"bSortable" : false,
				"aTargets" : [ 0 ]
			} ],
			"aaSorting" : [ [ 1, 'asc' ] ],
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			],
			// set the initial value
			"iDisplayLength" : -1,
		});

	},
	query : function() {
		if (listForm.oTable) {
			listForm.oTable.dataTableSettings[0]._iDisplayStart = 0;
			listForm.oTable.fnDraw();
		}
	},

};