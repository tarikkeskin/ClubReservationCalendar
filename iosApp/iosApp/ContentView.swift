import SwiftUI
import shared
import FirebaseCore
import FirebaseFirestore
import FirebaseAuth
import FirebaseStorage

struct ContentView: View {
    
    var strr:String
    init(str:String){
        strr = str
    }
    
    @State var isPickerShowing = false
    @State var selectedImage: UIImage?
    
	var body: some View {
        ComposeView(str:strr)
	}
    
    
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView(str:"d")
	}
}

class UploadImageToFirebaseStorage{
    
    func uploadPhoto(imageData: Data){
        
        let storageRef = Storage.storage().reference()
        
        let fileRef = storageRef.child("images/\(UUID().uuidString).jpg")
        
        let uploadTask = fileRef.putData(imageData, metadata: nil){
            metadata,error in
            if error == nil && metadata != nil{
                //save ref to firestore
            }
        }
    }
}
