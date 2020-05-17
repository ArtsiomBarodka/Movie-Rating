<%--
  Created by IntelliJ IDEA.
  User: Artsiom
  Date: 17.05.2020
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Upload image</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="fileUploadForm" method="POST">
                    <div class="form-group">
                        <label for="inputFile">Example file input</label>
                        <input type="file" name="image" class="form-control-file" id="inputFile" accept=".jpg, .jpeg, .png">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-dark px-4" form="fileUploadForm">Save</button>
                <button type="button" class="btn btn-dark px-4" data-dismiss="modal">Back</button>
            </div>
        </div>
    </div>
</div>