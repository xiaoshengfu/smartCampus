function loadpage(visiblePages,currentPage,total,pageSize,href,parameter) {
    var totalPages = total % pageSize > 0 ? (total / pageSize) + 1 : (total / pageSize);
    $.jqPaginator('#pagination', {
        totalPages: totalPages,
        visiblePages: visiblePages,
        currentPage: currentPage,
        first: '<li class="first"><a href="javascript:;">首页</a></li>',
        prev: '<li class="prev"><a href="javascript:;"><i class="arrow arrow2"></i>上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页<i class="arrow arrow3"></i></a></li>',
        last: '<li class="last"><a href="javascript:;">末页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            if (type == "change") {
            	location.href=href+"?page="+num+parameter;
            }
        }
    });
}