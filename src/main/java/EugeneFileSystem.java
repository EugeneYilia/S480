import java.util.*;

// 设计一个内存文件系统，模拟以下功能：
//
// 实现文件系统类:
//
// FileSystem() 初始化系统对象
// List<String> ls(String path)
// 如果 path 是一个文件路径，则返回一个仅包含该文件名称的列表。
// 如果 path 是一个目录路径，则返回该目录中文件和 目录名 的列表。
//           答案应该 按字典顺序 排列。
//
// void mkdir(String path) 根据给定的路径创建一个新目录。给定的目录路径不存在。如果路径中的中间目录不存在，您也应该创建它们。
// void addContentToFile(String filePath, String content)
// 如果 filePath 不存在，则创建包含给定内容 content的文件。
// 如果 filePath 已经存在，将给定的内容 content附加到原始内容。
// String readContentFromFile(String filePath) 返回 filePath下的文件内容。
public class EugeneFileSystem {

    public static void main(String[] args) {
        var fs = new EugeneFileSystem();
        System.out.println(fs.ls("/"));
        fs.mkdir("/a/e");
        fs.mkdir("/a/b/c");
        fs.addContentToFile("/a/b/c/d", "hello");
        System.out.println(fs.ls("/"));
        System.out.println(fs.readContentFromFile("/a/b/c/d"));
        System.out.println(fs.ls("/a/b"));
        System.out.println(fs.ls("/a"));
    }

    private final TreeMap<String, FileNode> rootNodes = new TreeMap<>();

    static class FileNode{
        public boolean isDirectory;
        public String name;
        public String content;
        public TreeMap<String, FileNode> subFiles;

        public FileNode(
            boolean isDirectory,
            String name,
            String content,
            TreeMap<String, FileNode> subFiles)
        {
            this.isDirectory = isDirectory;
            this.name = name;
            this.content = content;
            this.subFiles = subFiles;
        }
    }

    public EugeneFileSystem() {

    }

    public List<String> ls(String path) {
        String[] filePath = path.split("/");
        List<String> result = new ArrayList<>();
        if(filePath.length == 0){
            result.addAll(rootNodes.keySet());
            return result;
        }

        FileNode fileNode = rootNodes.get(filePath[1]);

        for (int i = 2; i < filePath.length; i++) {
            fileNode = fileNode.subFiles.get(filePath[i]);
        }


        if(fileNode.isDirectory) {
            result.addAll(fileNode.subFiles.keySet());
        } else {
            result.add(fileNode.name);
        }
        return result;
    }

    public void mkdir(String path) {
        String[] filePath = path.split("/");
        FileNode fileNode = rootNodes.computeIfAbsent(filePath[1],_ -> new FileNode(true, filePath[1], null, new TreeMap<>()));

        for (int i = 2; i < filePath.length; i++) {
            String fileName = filePath[i];
            fileNode = fileNode.subFiles.computeIfAbsent(fileName, _ -> new FileNode(true, fileName, null, new TreeMap<>()));
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] path = filePath.split("/");
        if(path.length == 2) {
            FileNode fileNode = rootNodes.get(path[1]);
            if(fileNode != null) {
                fileNode.content += content;
            } else {
                rootNodes.put(path[1], new FileNode(false, path[1], content, null));
            }
        } else {
            FileNode fileNode = rootNodes.get(path[1]);
            for (int i = 2; i < path.length - 1; i++) {
                String fileName = path[i];
                fileNode = fileNode.subFiles.get(fileName);
            }

            String fileName = path[path.length - 1];
            FileNode file = fileNode.subFiles.get(fileName);
            if(file != null) {
                file.content += content;
            } else {
                fileNode.subFiles.put(fileName, new FileNode(false, fileName, content, null));
            }
        }
    }

    public String readContentFromFile(String filePath) {
        String[] path = filePath.split("/");
        if(path.length == 2) {
            return rootNodes.get(path[1]).content;
        } else {
            FileNode fileNode = rootNodes.get(path[1]);
            for (int i = 2; i < path.length; i++) {
                String fileName = path[i];
                fileNode = fileNode.subFiles.get(fileName);
            }
            return fileNode.content;
        }
    }
}
