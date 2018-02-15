package csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVUtilities {

	private ArrayList<String> CSVData;
	private File file;
	public File getFile() {
		return file;
	}

	public ArrayList<String> getCSVData() {
		return CSVData;
	}

	private int numColumns;
	private String delimiter;
	
	
	public String getDelimiter() {
		return delimiter;
	}

	/**
	 * adds each row of data into the list CSVData
	 * @param csv the csv file to be read
	 */
	public CSVUtilities(File csv, String delimiter) {
		this.file = csv;
		this.delimiter = delimiter;
		CSVData = new ArrayList<String>();
		try (BufferedReader input = new BufferedReader(new FileReader(csv))) {
			String line;
			while ((line = input.readLine()) != null) {
				CSVData.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Test");
	}

	/**
	 * @return an ArrayList with the headers for each column
	 */
	public List<String> getColumnHeaders() {
		return Arrays.asList(CSVData.get(0).split(this.delimiter));
	}

	/**
	 * @param column the column index
	 * @return an ArrayList with the data for a column specified
	 */
	public List<String> getDataString(int column) {
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < CSVData.size(); i++) {
			String[] row = CSVData.get(i).split("\\" + delimiter, 2);
			data.add(row[column]);
		}
		return data;
 	}

	/**
	 * @param column the column index
	 * @return an ArrayList with the data converted to Integer
	 */
	public List<Integer> getDataInt(int column) {
		List<Integer> data = new ArrayList<Integer>();
		for (int i = 0; i < CSVData.size(); i++) {
			String[] row = CSVData.get(i).split(this.delimiter);
			String dataCellString = row[column];
			Integer dataCellInteger = null;
			try {
				dataCellInteger = Integer.parseInt(dataCellString);
			} catch(NumberFormatException e) {
				continue;
			} finally {
				data.add(dataCellInteger);
			}
		}
		return data;
	}

	/**
	 * @param column the column index
	 * @return an ArrayList with the data converted to Double
	 */
	public List<Double> getDataDouble(int column) {
		List<Double> data = new ArrayList<Double>();
		for (int i = 1; i < CSVData.size(); i++) {
			String[] row = CSVData.get(i).split(this.delimiter);
			String dataCellString = row[column];
			Double dataCellDouble = null;
			try {
				dataCellDouble = Double.parseDouble(dataCellString);
			} catch(NumberFormatException e) {
				continue;
			} finally {
				data.add(dataCellDouble);
			}
		}
		return data;
	}

	/**
	 * @param column the column index
	 * @param count the number of rows
	 * @return an ArrayList with the data for a column specified
	 */
	public List<String> getDataString(int column, int count) {
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			String[] row = CSVData.get(i).split("\\" + this.delimiter);
			data.add(row[column]);
		}
		return data;
 	}
	
	/**
	 * @param column the column index
	 * @param count the number of rows that are not null
	 * @return an ArrayList with the data converted to Integer
	 */
	public Map<Integer, Integer> getDataInt(int column, int count) {
		Map<Integer, Integer> indexAndData = new HashMap<Integer, Integer>();
		int i = 0;
		while (i < count && i < CSVData.size()) {
			String[] row = CSVData.get(i).split(this.delimiter);
			String dataCellString = row[column];
			Integer dataCellInt = null;
			try {
				dataCellInt = Integer.parseInt(dataCellString);
			} catch(NumberFormatException e) {
				i++;
				count++;
				continue;
			}
			indexAndData.put(i, dataCellInt);
			i++;
		}
		return indexAndData;
	}

	/**
	 * @param column the column index
	 * @param count the number of rows that are not null
	 * @return an ArrayList with the data converted to Double
	 */
	public Map<Integer, Double> getDataDouble(int column, int count) {
		Map<Integer, Double> indexAndData = new HashMap<Integer, Double>();
		int i = 0;
		while (i < count && i < CSVData.size()) {
			String[] row = CSVData.get(i).split(this.delimiter);
			String dataCellString = row[column];
			Double dataCellDouble = null;
			try {
				dataCellDouble = Double.parseDouble(dataCellString);
			} catch(NumberFormatException e) {
				i++;
				count++;
				continue;
			}
			indexAndData.put(i, dataCellDouble);
			i++;
		}
		return indexAndData;
	}
	
	public String getData(int rowIdx, int columnIdx) {
		String[] row = CSVData.get(rowIdx).split(this.delimiter);
		return row[columnIdx];
 	}
	
	public List<Entity> getAllEntities(int primaryKeyCol, String primaryKey) {
		List<String> headers = getColumnHeaders();
		List<Entity> entities = new ArrayList<>();
		String[] line = null;
		for (int i = 0; i < CSVData.size(); i++) {
			if ((line = CSVData.get(i).split(this.delimiter))[primaryKeyCol].equals(primaryKey)) {
				Entity ent = new Entity(primaryKey);
				for (int j = 0; j < headers.size(); j++) {
					ent.setAttribute(headers.get(j), line[j]);
				}
				entities.add(ent);
			}
		}
		return entities;
 	}
	
	public List<Entity> getAllEntities(int primaryKeyCol) {
		List<String> headers = getColumnHeaders();
		List<Entity> entities = new ArrayList<>();
		for (int i = 1; i < CSVData.size(); i++) {
			String[] line = CSVData.get(i).split(this.delimiter);
			Entity ent = new Entity(line[primaryKeyCol-1]);
			for (int j = 0; j < headers.size() - 1; j++) {
				ent.setAttribute(headers.get(j), line[j]);
			}
			entities.add(ent);
		}
		return entities;
 	}
	
	public Entity getEntity(int primaryKeyCol, String primaryKey) {
		String[] line = null;
		for (int i = 0; i < CSVData.size(); i++) {
			if ((line = CSVData.get(i).split(this.delimiter))[primaryKeyCol].equals(primaryKey)) {
				break;
			}
		}
		List<String> headers = getColumnHeaders();
		Entity ent = new Entity(primaryKey);
		for (int i = 0; i < headers.size(); i++) {
			ent.setAttribute(headers.get(i), line[i]);
		}
		return ent;
 	}
	
	public Entity getEntity(int primaryKeyCol, String primaryKey, int start) {
		String[] line = null;
		for (int i = start; i < CSVData.size(); i++) {
			if ((line = CSVData.get(i).split(this.delimiter))[primaryKeyCol].equals(primaryKey)) {
				break;
			}
		}
		List<String> headers = getColumnHeaders();
		Entity ent = new Entity(primaryKey);
		for (int i = 0; i < headers.size(); i++) {
			ent.setAttribute(headers.get(i), line[i]);
		}
		return ent;
 	}
	
	public List<String> getData(int col, Collection<Integer> indices) {
		List<String> data = new ArrayList<String>();
		for (int i : indices) {
			data.add(getData(i, col));
		}
		return data;
 	}

	public int getNumColumns() {
		return this.numColumns;
	}
	
	public void write(String line) {
		try (FileWriter pw = new FileWriter(this.file, true);) {
			for (String s : line.split(this.delimiter)) {
				pw.append(s);
			}
			pw.append('\n');
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}