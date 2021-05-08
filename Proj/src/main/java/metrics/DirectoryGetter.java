package metrics;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 
 * Gets the directory of a project
 *
 */
public class DirectoryGetter {

	private File baseFile;
	private File src;
	private List<Package> Packages = new ArrayList<Package>();

	/**
	 * Gets the file in the base of the project
	 * 
	 * @return the file in the base of the project
	 */
	public File getBaseFile() {
		if (baseFile.exists()) {
			return baseFile;
		} else {
			return null;
		}
	}

	/**
	 * Gets the src of the project
	 * 
	 * @return the src of the project
	 */
	public File getsrc() {
		if (src.exists()) {
			return src;
		} else {
			return null;
		}
	}

	/**
	 * Gets the number of packages
	 * 
	 * @return the number of packages
	 */
	public int getPackageNumber() {
		return Packages.size();
	}

	/**
	 * Gets the list of packages
	 * 
	 * @return the list of packages
	 */
	public List<Package> getPackages() {
		return Packages;
	}

	/**
	 * Sets a new directory to the base file
	 * 
	 * @param dir string of the directory
	 */
	public void SetDir(String dir) throws IllegalArgumentException {
		baseFile = new File(dir);
		if (!baseFile.exists()) {
			throw new IllegalArgumentException("Can't locate directory " + dir + " in the local files");
		}
	}

	/**
	 * Creates the src
	 */
	public void FindSrc() throws IllegalStateException {
		String[] var = baseFile.list();
		if (Arrays.asList(var).contains("src")) {
			src = new File(baseFile, "src");
		} else {
			throw new IllegalStateException("Directory isn't a java project (can't locate src)");
		}
	}

	/**
	 * Calls createPackage and initializes the list of packages
	 */
	public void FindPackages() {
		Packages = createPackage("", src, Packages);
	}

	/**
	 * Creates the packages
	 * 
	 * @param path string of the path
	 * @param file to which the packages will be added
	 * @param lst  list of packages to create
	 * @return list of packages created
	 */
	private List<Package> createPackage(String path, File file, List<Package> lst) {

		// todos os ficheiros que sao diretorias aka packages
		List<File> dir = Arrays.asList(file.listFiles()).stream().filter(p -> p.isDirectory() == true)
				.collect(Collectors.toList());
		if (dir.isEmpty()) {

			// System.out.println(file.toString());
			List<Package> r = new ArrayList<Package>();
			r.add(new Package(path.substring(0, path.length() - 1), file));
			return r;
		} else {
			for (File x : dir) {
				lst.addAll(createPackage(path + x.getName() + ".", x, new ArrayList<Package>()));
			}
		}
		return lst;
	}

	/**
	 * Gets the package list
	 * @return the package list
	 */
	public List<Package> getPackageList() {
		return Packages;
	}

}
