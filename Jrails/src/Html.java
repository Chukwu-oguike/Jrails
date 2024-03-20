package jrails;

public class Html {

    public String htmlChain;
    public String tName;

    Html(String chain, String name){
        this.htmlChain = chain;
        tName = name;
    }
    public String toString() {
        return htmlChain;
    }

    public Html seq(Html h) {

        String hChain = this.htmlChain + h.htmlChain;
        Html temp = new Html(hChain, h.tName);
        return temp;
    }

    public Html br() {

        String hChain = "<br>" + this.htmlChain + "<br/>";
        Html temp = new Html(hChain, this.tName);
        return temp;
    }

    public Html t(Object o) {

        String hChain = this.htmlChain + o.toString();

        Html temp = new Html(hChain, o.toString());
        return temp;
    }

    public Html p(Html child) {

        String hChain = this.htmlChain + "<p>"+child.htmlChain+"</p>";
        Html temp = new Html(hChain, child.tName);
        return temp;

    }

    public Html div(Html child) {
        String hChain = this.htmlChain + "<div>"+child.htmlChain+"</div>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public Html strong(Html child) {

        String hChain = this.htmlChain + "<strong>"+child.htmlChain+"</strong>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public Html h1(Html child) {

        String hChain = this.htmlChain + "<h1>"+child.htmlChain+"</h1>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public Html tr(Html child) {

        String hChain = this.htmlChain + "<tr>"+child.htmlChain+"</tr>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public Html th(Html child) {

        String hChain = this.htmlChain + "<th>"+child.htmlChain+"</th>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public Html td(Html child) {

        String hChain = this.htmlChain + "<td>"+child.htmlChain+"</td>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public Html table(Html child) {

        String hChain = this.htmlChain + "<table>"+child.htmlChain+"</table>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public Html thead(Html child) {


        String hChain = this.htmlChain + "<thead>"+child.htmlChain+"</thead>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public Html tbody(Html child) {

        String hChain = this.htmlChain + "<tbody>"+child.htmlChain+"</tbody>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public Html textarea(String name, Html child) {

        String hChain = this.htmlChain + "<textarea name=\""+child.tName+"\">"+child.htmlChain+"</textarea>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public Html link_to(String text, String url) {

        String hChain = this.htmlChain + "<a href=\""+url+"\">"+text+"</a>";
        Html temp = new Html(hChain,"");
        return temp;
    }

    public Html form(String action, Html child) {

        String hChain = this.htmlChain + "<form action=\"/"+action+"\" accept-charset=\"UTF-8\" method=\"post\">"+child.htmlChain+"</form>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public Html submit(String value) {

        String hChain = this.htmlChain + "<input type=\"submit\" value=\""+value+"\"/>";
        Html temp = new Html(hChain, "");
        return temp;
    }
}