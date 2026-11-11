const studentForm = document.getElementById("studentForm");
const message = document.getElementById("message");
const searchStudent = document.getElementById('searchStudent');
const clearBtn = document.getElementById('clearSearch');
const cancelUpdate = document.getElementById("cancelUpdate");

clearBtn.addEventListener('click', function(){
    searchStudent.value = "";
    fetchAllStudent();
})

searchStudent.addEventListener('input', function(){
    fetchAllStudent();
})

studentForm.addEventListener('submit', function(event) {
    event.preventDefault();
    message.innerText="";

    const name = document.getElementById('name').value;
    const rollNum = Number(document.getElementById('rollNum').value);
    const email = document.getElementById('email').value;
    const subject = document.getElementById('subject').value;
    const age = Number(document.getElementById('age').value);

    if(name.trim() == ""){
        alert("Please entet the name!");
        return;
    }
    else if(isNaN(rollNum) || rollNum <= 0){
        alert('Please enter the correct rollnum');
        return;
    }
    else if(email.trim() == "" || !email.includes('@') || !email.includes(".")){
        alert("Please entet the email!");
        return;
    }
    else if(subject.trim() == ""){
        return alert('Please enter the subject');
    }
    else if(age <= 0 || isNaN(age)){
        alert('Please enter the correct age!');
        return;
    }

    const studentInfo = {
        name: name,
        rollNum: rollNum,
        email: email,
        subject: subject,
        age: age
    };

    let url;
    let method;

      if(updateStudentId == null){
            url =  "http://localhost:8080/api/studentInformation/save";
            method = 'POST';
            message.innerText = "Student saved successfully!";
      }    
      else{
            url = `http://localhost:8080/api/studentInformation/updateStudent?id=${updateStudentId}`;
            method = 'PUT';
            message.innerText = "Student updated successfully!";
      }

    fetch(url, {
        method: method,
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(studentInfo)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Student save failed!");
        }

        return response.text();
    })
    .then(data => {
        // console.log("Student saved successfully!", data);
        // message.innerText = "Student saved successfully!";

            updateStudentId = null;
            studentForm.reset();

            document.getElementById('submitBtn').innerText = "Add Student";

            fetchAllStudent();
    })
    .catch(error => {
        // console.log("Error:", error);
    });
});


function fetchAllStudent() {
    const studentTableBody = document.getElementById("studentTableRows");
    studentTableBody.innerHTML = "";

    fetch("http://localhost:8080/api/studentInformation/fetchAllStudent")
    .then(response => {
        if (!response.ok) {
            throw new Error("Unable to fetch students!");
        }

        return response.json();
    })
    .then(data => {

        let searchText = searchStudent.value.trim().toLowerCase();

        let result = data.filter(student => 
            student.name.toLowerCase().includes(searchText) ||
            student.rollNum.toString().includes(searchText)
        );

        result.forEach(student => {
            studentTableBody.innerHTML += `
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.rollNum}</td>
                    <td>${student.email}</td>
                    <td>${student.subject}</td>
                    <td>${student.age}</td>
                    <td>
                        <button class="deleteBtn" type="button" onclick="deleteStudent(${student.id})">
                            Delete
                        </button>

                        <button class="updateBtn" type="button" onclick="editStudent(${student.id})">
                            Update
                        </button>
                    </td>
                </tr>
            `;

        });
        if (result.length == 0) {
            studentTableBody.innerHTML = `
        <tr>
            <td colspan="7">No students found!</td>
        </tr>
        `;
    return;
}
    })
    .catch(error => {
        // console.log("Error fetching students:", error);
    });
}


function deleteStudent(id) {

    let con = confirm("are you sure?");

    if(con == true){

    fetch(`http://localhost:8080/api/studentInformation/deletestudent?id=${id}`, {
        method: "DELETE"
    })
    .then(response => {

        if (!response.ok) {
            throw new Error("Student delete failed!");
        }
        // console.log("Student deleted successfully!");

        message.innerText = "student deleted successfully!";

        fetchAllStudent();
    })
    .catch(error => {
        // console.log("Error:", error);
    });
    }
}


let updateStudentId = null;

function editStudent(id) {

    updateStudentId = id;

    // console.log("Selected Student ID:", updateStudentId);

    fetch(`http://localhost:8080/api/studentInformation/fetchStudent?id=${id}`)
    .then(response => {
        // console.log("Response Status:", response.status);

        if (!response.ok) {
            throw new Error("Student not found!");
        }

        return response.json();
    })
    .then(student => {

        // console.log("Student Data:", student);

        document.getElementById('name').value = student.name;
        document.getElementById('rollNum').value = student.rollNum;
        document.getElementById('email').value = student.email;
        document.getElementById('subject').value = student.subject;
        document.getElementById('age').value = student.age;

        document.getElementById('submitBtn').innerText = "Update Student";

    })
    .catch(error => {
        // console.log("Error:", error);
    });
    cancelUpdate.style.display = 'block';
}

cancelUpdate.addEventListener('click', function(){
    studentForm.reset();
    updateStudentId = null;
    document.getElementById('submitBtn').innerText = "Add Student";

    cancelUpdate.style.display = 'none';
});

fetchAllStudent();