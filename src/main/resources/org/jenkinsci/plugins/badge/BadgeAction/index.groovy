package org.jenkinsci.plugins.badge.BadgeAction

def l = namespace(lib.LayoutTagLib)
def st = namespace("jelly:stapler")

l.layout {
    l.main_panel {
        h2(_("Embeddable Badges"))
        p(raw(_("blurb")))
        raw("""
<p>
</p>
<script>
    Behaviour.register({
        "INPUT.select-all" : function(e) {
            e.onclick = function () {
                e.focus();
                e.select();
            }
        }
    });
</script>
<style>
    INPUT.select-all {
        width:100%;
    }
    IMG#badge {
        margin-left:2em;
    }
</style>
""")

        def fullJobName = h.escape(my.project.fullName);
        def jobUrlWithoutView =  "${app.rootUrl}job/${fullJobName}";
        def buildStatus = "${app.rootUrl}buildStatus/buildIcon?job=${fullJobName}";
        def coverage = "${app.rootUrl}buildStatus/coverageIcon?job=${fullJobName}"

		h2(_("Build Status"))
		h3 {
            img(id:"buildStatus",src:buildStatus)
        }

        h3(_("Markdown"))
        input(type:"text",value:"[![Build Status](${buildStatus})](${jobUrlWithoutView})",class:"select-all")

        h3(_("Confluence"))
        input(type:"text",value:"[!${buildStatus}!|${jobUrlWithoutView}]",class:"select-all")

        h2(_("Code Coverage"))
        h3 {
            img(id:"coverage",src:coverage)
        }

        h3(_("Markdown"))
        input(type:"text",value:"[![Code Coverage](${coverage})](${jobUrlWithoutView})",class:"select-all")

        h3(_("Confluence"))
        input(type:"text",value:"[!${coverage}!|${jobUrlWithoutView}]",class:"select-all")
    }
}
