//package com.example.android.teamnahhseproject;
//
//import org.junit.Before;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.auth.FirebaseAuth;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.stubbing.Answer;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.powermock.modules.junit4.PowerMockRunnerDelegate;
//
//import static org.mockito.Matchers.any;
//import static org.mockito.Matchers.anyString;
//import static org.mockito.Mockito.doAnswer;
//import static org.mockito.Mockito.when;
//
//@RunWith(PowerMockRunner.class)
//@PowerMockRunnerDelegate(JUnit4.class)
//@PrepareForTest({ FirebaseDatabase.class})
//public class RegistrationActivityTest {
//
//    private DatabaseReference mockedDatabaseReference;
//    private FirebaseAuth mockAuth;
//    private FirebaseDatabase mockDatabase;
//
//    @Before
//    public void setUp() throws Exception {
//
//        mockedDatabaseReference = Mockito.mock(DatabaseReference.class);
//
//        FirebaseDatabase mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
//        when(mockedFirebaseDatabase.getReference()).thenReturn(mockedDatabaseReference);
//
//        PowerMockito.mockStatic(FirebaseDatabase.class);
//        when(FirebaseDatabase.getInstance()).thenReturn(mockedFirebaseDatabase);
//
//    }
//
//    @Test
//    public void someTest() {
//        mockedDatabaseReference = Mockito.mock(DatabaseReference.class);
//        mockAuth = Mockito.mock(FirebaseAuth.class);
//        mockDatabase = Mockito.mock(FirebaseDatabase.class);
//        mockDatabase = FirebaseDatabase.getInstance();
//
//        Mockito.when(mockFirestore.someMethodCallYouWantToMock()).thenReturn(something);
//        Mockito.when(mockDatabase.get)
//
//        DatabaseInteractor interactor = new DatabaseInteractor(mockFirestore)
//
//        // some assertion or verification
//    }
//}