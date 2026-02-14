import React, { useState, useEffect } from 'react';
import { X, Save, User } from 'lucide-react';
import { useAuth } from '../../context/AuthContext';

export function EditProfileModal({ onClose }) {
    const { user, updateUser } = useAuth();
    const [formData, setFormData] = useState({
        firstname: '',
        lastname: '',
        username: '',
        email: '',
        bio: ''
    });

    useEffect(() => {
        if (user) {
            setFormData({
                firstname: user.firstname || '',
                lastname: user.lastname || '',
                username: user.username || '',
                email: user.email || '',
                bio: user.bio || ''
            });
        }
    }, [user]);

    const handleSubmit = (e) => {
        e.preventDefault();
        updateUser(formData);
        onClose();
    };

    return (
        <div className="modal fade show d-block" style={{ backgroundColor: 'rgba(0,0,0,0.5)' }} tabIndex="-1">
            <div className="modal-dialog modal-dialog-centered">
                <div className="modal-content animate-fade-in border-0 shadow">
                    <div className="modal-header border-bottom-0 pb-0">
                        <h5 className="modal-title fw-bold">Edit Profile</h5>
                        <button type="button" className="btn-close" onClick={onClose} aria-label="Close"></button>
                    </div>

                    <div className="modal-body">
                        <form onSubmit={handleSubmit}>
                            <div className="row g-2 mb-3">
                                <div className="col-6">
                                    <label className="form-label fw-semibold">First Name</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        value={formData.firstname}
                                        onChange={e => setFormData({ ...formData, firstname: e.target.value })}
                                        required
                                    />
                                </div>
                                <div className="col-6">
                                    <label className="form-label fw-semibold">Last Name</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        value={formData.lastname}
                                        onChange={e => setFormData({ ...formData, lastname: e.target.value })}
                                        required
                                    />
                                </div>
                            </div>

                            <div className="mb-3">
                                <label className="form-label fw-semibold">Username</label>
                                <div className="input-group">
                                    <span className="input-group-text bg-body-secondary text-muted">@</span>
                                    <input
                                        type="text"
                                        className="form-control"
                                        value={formData.username}
                                        onChange={e => setFormData({ ...formData, username: e.target.value })}
                                        required
                                    />
                                </div>
                            </div>

                            <div className="mb-3">
                                <label className="form-label fw-semibold">Email</label>
                                <input
                                    type="email"
                                    className="form-control"
                                    value={formData.email}
                                    onChange={e => setFormData({ ...formData, email: e.target.value })}
                                    required
                                />
                            </div>

                            <div className="mb-4">
                                <label className="form-label fw-semibold">Bio</label>
                                <textarea
                                    className="form-control"
                                    rows="3"
                                    placeholder="Tell us a bit about yourself..."
                                    value={formData.bio}
                                    onChange={e => setFormData({ ...formData, bio: e.target.value })}
                                />
                                <div className="form-text">Share your culinary interests.</div>
                            </div>

                            <button type="submit" className="btn btn-primary w-100 d-flex align-items-center justify-content-center gap-2 py-2">
                                <Save size={20} /> Save Changes
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}
