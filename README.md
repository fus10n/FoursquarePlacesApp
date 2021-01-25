# Foursquare places demo app

### Architectural pattern
I've chosen my favourite MVP (model-view-presenter). It's advantage over the old MVC pattern is improved
availability for testing and also stripping activities off code which is not UI related,
improving readability and possibly re-using the same code.
Regardless of the small size of the project, I've provided good code separation for the different
logical units of the project (UI, networking, domains).

### User Interface (UI)
Simple LinearLayout as the UI only contains 2 views.
RecyclerView for listing the places - more optimized than a standard ListView due to recycling of
items that have been scrolled out of the screen.

### In-code communication
I've made use of the 3rd party EventBus. In my opinion it's the most convenient method for passing
data between different entities of the project.  Passing interface callbacks is also an option,
however in more complex code structures it's more difficult to achieve and results in stacking of
interfaces and functions, which leads to poor readability.

### Functionality algorithm
1. User types a search phrase in the search field
2. The text is automatically analysed by the TextWatcher on the input field (EditText)
3. An HTTPS "GET" request is automatically sent from the RequestManager to the backend with certain request parameters, including the search phrase
4. Backend responds with a JSON containing all places that reflect the request parameters, again inside the RequestManager
5. RequestManager calls the ContentManager to parse and process the JSON response.
6. ContentManager builds UI content out of the JSON data and sends it via the EventBus
7. MainActivity is subscribed to that event, catches it and forwards the received content to the recycler adapter.
8. The same content is finally shown to the user in the shape of a list of places.
9. User is free to repeat the process infinite times. Subsequent searches will replace the existing content with the new one.

### Tests
Due to the small size of the project, the only thing I believe could be tested is the RecyclerView Adapter.
It contains a custom setItems() function that's supposed to erase the current items and add the new ones.
Therefore I have implemented a JUnit test which ensures that function is working correctly.

### Further development
Provided more time and more functionality features, I would have written some proguard rules
for better obfuscation and also more tests.

### Problems
Currently the HTTPS request responds with the following JSON message:
**{"meta":{"code":400,"errorType":"invalid_auth","errorDetail":"Missing access credentials.....**
That results in no places being fetched and none showing up on in the app.
