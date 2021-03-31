package metrics;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DirectoryGetter {
	
	
	private File baseFile;
	private File src;
	private List<Package> Packages= new ArrayList<Package>();
	
	
	public void SetDir(String dir) throws IllegalArgumentException {
		baseFile= new File(dir);
		if(!baseFile.exists()) {
			throw new IllegalArgumentException("Can't locate directory "+dir+" in the local files");
		}		
	}
	
	
	public void FindSrc() throws IllegalStateException {
		String[] var =baseFile.list();
		if(Arrays.asList(var).contains("src")){
			src= new File(baseFile,"src");
		}else {
			throw new IllegalStateException("Directory isn't a java project (can't locate src)");
		}
	}
	
	public void FindPackages() {
		Packages=createPackage("", src, Packages);
	}
	
	
	//metodo recursivo que cria todas as packages (boa sorte a perceber isto)
	private List<Package> createPackage(String path, File file, List<Package> lst) {
		
		//todos os ficheiros que sao diretorias aka packages
		List<File> dir=Arrays.asList(file.listFiles()).stream().filter(p->p.isDirectory()==true).collect(Collectors.toList());
		if(dir.isEmpty()) {
			
			//System.out.println(file.toString());
			List<Package> r=new ArrayList<Package>();
			r.add(new Package(path.substring(0,path.length()-1), file));
			return r;
		}else {
			for(File x: dir) {
				lst.addAll(createPackage(path+x.getName()+".", x, new ArrayList<Package>()));
			}
		}
		return lst;
	}
	
	
	public List<Package> getPackageList(){
		return Packages;
	}

}
