This is a Github repository containing our group's work for the SC2002's (Object-Oriented Programming) Final Project.

## Folder Structure

- `src` contains our source code
- `data` contains the data we work on
- `umls` contains the UML diagrams

## How to use HashMap

- Initialise: HashMap<Key_Type, Element_Type> hashMapName = new HashMap<>(); 
- Here, elements are addressed by keys instead of index like Array
- Get element: Element_Type element = hashMapName.get(key);
- Add element: hashMapName.put(key, element);
- Delete element: hashMapName.remove(key);
- Check size: hashMapName.size();
- Iterate through the HashMap: for (Map.Entry<Key_Type, Element_Type> e : hashMapName.entrySet() ) { //iterate, call e.getKey(); to get key and e.getValue() to get element }