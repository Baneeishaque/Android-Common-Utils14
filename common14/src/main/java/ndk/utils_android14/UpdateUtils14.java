package ndk.utils_android14;

public class UpdateUtils14 {

    //TODO : Not compatiable with SDK 14
    // public static FireStoreRequestResponse getServerVersionFireStore(FirebaseFirestore firebaseFirestoreDb, String applicationName, Context applicationContext) {

    //     FireStoreRequestResponse fireStoreRequestResponse = new FireStoreRequestResponse(2, Map.of(), new Exception());

    //     // DocumentReference documentReference = firebaseFirestoreDb.collection("configuration").document("1");

    //     // documentReference.get().addOnCompleteListener(task -> {

    //     //     if (task.isSuccessful()) {

    //     //         DocumentSnapshot document = task.getResult();
    //     //         if (document != null && document.exists()) {

    //     //             LogUtils.debug(applicationName, "DocumentSnapshot data: " + document.getData());

    //     //             fireStoreRequestResponse.setStatus(0);
    //     //             fireStoreRequestResponse.setData(document.getData());

    //     //         } else {

    //     //             LogUtils.debug(applicationName, "No such document...");

    //     //             fireStoreRequestResponse.setStatus(1);
    //     //         }
    //     //     } else {

    //     //         ExceptionUtils1.handleExceptionOnGui(applicationContext, applicationName, task.getException());

    //     //         fireStoreRequestResponse.setStatus(-1);
    //     //         fireStoreRequestResponse.setException(task.getException());
    //     //     }
    //     // });
    //     // while (fireStoreRequestResponse.getData().isEmpty()) {
    //     // }
    //     return fireStoreRequestResponse;
    // }
}