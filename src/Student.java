public class Student {
    private String id, name, branch, contact;

    public Student(String id, String name, String branch, String contact) {
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.contact = contact;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getBranch() { return branch; }
    public String getContact() { return contact; }

    public void show() {
        System.out.println(id + " | " + name + " | " + branch + " | " + contact);
    }
}
