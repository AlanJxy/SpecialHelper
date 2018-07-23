;
(function($, window, document, undefined) {
	var pluginName = "table2excel",

		defaults = {
			exclude: ".noExl",
			name: "Table2Excel"
		};

	function Plugin(element, options) {
		this.element = element;
		this.settings = $.extend({}, defaults, options);
		this._defaults = defaults;
		this._name = pluginName;
		this.init();
	}

	Plugin.prototype = {
		init: function() {
			var e = this;

			var utf8Heading = "<meta http-equiv=\"content-type\" content=\"application/vnd.ms-excel; charset=UTF-8\">";
			e.template = {
				head: "<html xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:x=\"urn:schemas-microsoft-com:office:excel\" xmlns=\"http://www.w3.org/TR/REC-html40\">" + utf8Heading + "<head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets>",
				sheet: {
					head: "<x:ExcelWorksheet><x:Name>",
					tail: "</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet>"
				},
				mid: "</x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body>",
				table: {
					head: "<table>",
					tail: "</table>"
				},
				foot: "</body></html>"
			};

			e.tableRows = [];

			$(e.element).each(function(i, o) {
				var tempRows = "";
				$(o).find("tr").not(e.settings.exclude).each(function(i, p) {
					tempRows += "<tr>";
					$(p).find("td").not(e.settings.exclude).each(function(i, q) {
						var flag = $(q).find(e.settings.exclude);
						if(flag.length >= 1) {
							tempRows += "<td> </td>";
						} else {
							tempRows += "<td>" + $(q).html() + "</td>";
						}
					});

					tempRows += "</tr>";
				});
				e.tableRows.push(tempRows);
			});

			e.tableToExcel(e.tableRows, e.settings.name, e.settings.sheetName);
		},

		tableToExcel: function(table, name, sheetName) {
			var e = this,
				fullTemplate = "",
				i, link, a;

			e.format = function(s, c) {
				return s.replace(/{(\w+)}/g, function(m, p) {
					return c[p];
				});
			};

			sheetName = typeof sheetName === "undefined" ? "Sheet" : sheetName;

			e.ctx = {
				worksheet: name || "Worksheet",
				table: table,
				sheetName: sheetName
			};

			fullTemplate = e.template.head;

			if($.isArray(table)) {
				for(i in table) {
					fullTemplate += e.template.sheet.head + sheetName + i + e.template.sheet.tail;
				}
			}

			fullTemplate += e.template.mid;

			if($.isArray(table)) {
				for(i in table) {
					fullTemplate += e.template.table.head + "{table" + i + "}" + e.template.table.tail;
				}
			}

			fullTemplate += e.template.foot;

			for(i in table) {
				e.ctx["table" + i] = table[i];
			}
			delete e.ctx.table;

			var isIE = /*@cc_on!@*/ false || !!document.documentMode;
			if(isIE) {
				if(typeof Blob !== "undefined") {
					fullTemplate = e.format(fullTemplate, e.ctx);
					fullTemplate = [fullTemplate];
					var blob1 = new Blob(fullTemplate, {
						type: "text/html"
					});
					window.navigator.msSaveBlob(blob1, getFileName(e.settings) + getFileExt(e.settings));
				} else {
					txtArea1.document.open("text/html", "replace");
					txtArea1.document.write(e.format(fullTemplate, e.ctx));
					txtArea1.document.close();
					txtArea1.focus();
					sa = txtArea1.document.execCommand("SaveAs", true, getFileName(e.settings) + getFileExt(e.settings));
				}
			} else {
				var blob = new Blob([e.format(fullTemplate, e.ctx)], {
					type: 'application/vnd.ms-excel'
				});
				window.URL = window.URL || window.webkitURL;
				link = window.URL.createObjectURL(blob);
				a = document.createElement("a");
				a.download = getFileName(e.settings) + getFileExt(e.settings);
				a.href = link;
				document.body.appendChild(a);
				a.click();
				document.body.removeChild(a);
			}
			return true;
		}
	};

	function getFileName(settings) {
		return(settings.filename ? settings.filename : "table2excel");
	}

	function getFileExt(settings) {
		return(settings.fileext ? settings.fileext : ".xls");
	}

	$.fn[pluginName] = function(options) {
		var e = this;
		e.each(function() {
			if(!$.data(e, "plugin_" + pluginName)) {
				$.data(e, "plugin_" + pluginName, new Plugin(this, options));
			}
		});
		return e;
	};

})(jQuery, window, document);