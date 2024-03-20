package jrails;

public class View {

    public static Html empty() {

        String hChain = "";
        Html temp = new Html(hChain, "");
        return temp;
    }

    public static Html br() {
        String hChain = "<br/>"+"";
        Html temp = new Html(hChain, "");
        return temp;
    }

    public static Html t(Object o) {
        Html temp = new Html(o.toString(), o.toString());
        return temp;
    }

    public static Html p(Html child) {
        String hChain = "<p>"+child.htmlChain+"</p>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public static Html div(Html child) {
        String hChain = "<div>"+child.htmlChain+"</div>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public static Html strong(Html child) {
        String hChain = "<strong>"+child.htmlChain+"</strong>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public static Html h1(Html child) {
        String hChain = "<h1>"+child.htmlChain+"</h1>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public static Html tr(Html child) {
        String hChain = "<tr>"+child.htmlChain+"</tr>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public static Html th(Html child) {

        String hChain = "<th>"+child.htmlChain+"</th>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public static Html td(Html child) {

        String hChain = "<td>"+child.htmlChain+"</td>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public static Html table(Html child) {
        String hChain = "<table>"+child.htmlChain+"</table>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public static Html thead(Html child) {

        String hChain = "<thead>"+child.htmlChain+"</thead>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public static Html tbody(Html child) {
        String hChain = "<tbody>"+child.htmlChain+"</tbody>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public static Html textarea(String name, Html child) {
        String hChain = "<textarea name=\"" + "name" + "\">"+child.htmlChain+"</textarea>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public static Html link_to(String text, String url) {
        String hChain = "<a href=\""+url+"\">"+text+"</a>";
        Html temp = new Html(hChain,"");
        return temp;
    }

    public static Html form(String action, Html child) {
        String hChain = "<form action=\""+action+"\" accept-charset=\"UTF-8\" method=\"post\">"+child.htmlChain+"</form>";
        Html temp = new Html(hChain, child.tName);
        return temp;
    }

    public static Html submit(String value) {
        String hChain = "<input type=\"submit\" value=\""+value+"\"/>";
        Html temp = new Html(hChain, "");
        return temp;
    }
}